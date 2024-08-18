# Patient Service

## Proje Hakkında

Patient Service, hastaların yönetimi için bir Spring Boot uygulamasıdır. Bu servis, hasta bilgilerini saklama, güncelleme, silme ve listeleme gibi işlemleri gerçekleştiren RESTful API'ler sunar. Ayrıca, RabbitMQ kullanarak bildirim servisleri ile entegrasyon sağlar.

## Özellikler

- RESTful API'ler ile hasta oluşturma, güncelleme, silme ve listeleme.
- CORS yapılandırması ile farklı frontend uygulamalarından API çağrıları yapılabilir.
- RabbitMQ kullanarak Notification Service ile entegrasyon.
- MySQL veritabanı ile veri saklama.

## Gereksinimler

- Java 11 veya daha üstü
- Maven 3.6 veya daha üstü
- MySQL 5.7 veya daha üstü
- RabbitMQ

## Kurulum

### 1. Projeyi Klonlayın

```bash
git clone https://github.com/kullaniciadi/patient-service.git
cd patient-service
