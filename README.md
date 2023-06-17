global:
  scrape_interval:     15s # Default scrape interval
  evaluation_interval: 15s

scrape_configs:
  - job_name: 'prometheus' # Job to scrape Prometheus metrics
    scrape_interval: 5s
    
    static_configs:
      - targets: ['localhost:9090']

  - job_name: 'spring-actuator'
    metrics_path: '/actuator/prometheus' # Job to scrape application metrics
    scrape_interval: 5s
    static_configs:
      - targets: ['172.19.37.125:8080']
