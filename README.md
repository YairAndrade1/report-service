# Report Service

This microservice generates reports of patients’ exam anomalies over a given period. It fulfills the ASR:

> **“Como médico especialista quiero obtener, en menos de 2 s, el reporte de todos los pacientes que en el último mes presentaron síntomas, agrupados por tipo de examen con anomalías.”**

---

## 📦 Prerequisitos

- Java 17+
- Maven
- JMeter (>= 5.0) para las pruebas de carga
- El **Exam Service** desplegado y accesible (por ejemplo en `http://<EXAM_VM_IP>:8080/examen`)

---

## 🚀 Despliegue

1. **Clona** este repositorio y compílalo:
   ```bash
   git clone https://github.com/tu‐usuario/report‐service.git
   cd report‐service
   mvn clean package -DskipTests
````

2. **Arranca** el servicio indicando el puerto y la URL del servicio de exámenes:

   ```bash
   java -jar target/report-service-0.0.1-SNAPSHOT.jar \
     --server.port=8081 \
     --exam.service.url=http://<EXAM_VM_IP>:8080/examen
   ```
3. Verifica que responde:

   ```bash
   curl -G http://localhost:8081/reports \
        --data-urlencode "from=2025-01-01T00:00:00"
   ```

---

## 🎯 Endpoint

`GET  /reports?from={ISO8601}`

* **from**: fecha/hora de inicio (ISO 8601).
* **Respuesta**:

  ```json
  {
    "period": "{from}_to_{now}",
    "groupBy": "examType",
    "data": [
      {
        "examType": "EEG",
        "count": 5,
        "patientIds": [12, 34, 56]
      },
      …
    ]
  }
  ```

---

## 🧪 Pruebas de carga con JMeter

1. **Crea un Test Plan** en JMeter:

   * **Thread Group**

     * Number of Threads (users): 50
     * Ramp-Up Period (s): 10
     * Loop Count: 100 (or Duration: 60 s)
2. **Configura HTTP Request Defaults** (opcional):

   * Protocol: `http`
   * Server Name or IP: `localhost`
   * Port Number: `8081`
3. **Añade un HTTP Request** dentro del Thread Group:

   * Method: `GET`
   * Path: `/reports`
   * Parameters → Name: `from`, Value: `2025-01-01T00:00:00`
4. **Añade un Listener**:

   * Summary Report
   * View Results in Table
5. **Ejecuta** el plan y observa:

   * **Average** < 2000 ms
   * **Error %** = 0%
6. (Opcional) **Linha de comando**:

   ```bash
   jmeter -n -t report_test_plan.jmx -l report_results.jtl
   ```

---

## 👍 Validación

Con `curl -w "\nTime: %{time_total}s\n"` obtuvimos:

```
Time: 1.04s
```

cumpliendo el ASR de latencia (< 2 s).
