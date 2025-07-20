# 🇧🇷 Aymoré – Navegação Soberana

**Aymoré** é um aplicativo Android que permite ao usuário navegar e ser localizado em aplicativos como Google Maps, Uber e iFood mesmo em situações de bloqueio, falha ou sabotagem do sistema GPS americano.

---

## 🛡️ Significado do nome

**Aymoré** é o nome de um povo indígena brasileiro historicamente conhecido por sua resistência à colonização.  
Esse nome simboliza **soberania, independência e identidade brasileira**, valores centrais deste projeto.  
É um grito tecnológico: **“não precisamos do GPS deles para saber onde estamos.”**

---

## 📲 Sobre o aplicativo

**Aymoré** é uma alternativa 100% em software ao sistema de localização via GPS.  
Em cenários onde o GPS esteja inacessível, bloqueado ou comprometido, o app ativa um "modo de navegação alternativa", estimando a localização por:

- Torres de celular
- Redes Wi-Fi próximas
- Sensores inerciais (IMU): acelerômetro, giroscópio e bússola
- Algoritmos de fusão e predição de movimento

O app injeta essa localização simulada no sistema via mock location para que outros apps (como Uber, Google Maps etc.) continuem funcionando normalmente.

---

## 🚀 MVP - Objetivo da Primeira Versão

- [x] Detectar ausência de GPS real
- [x] Estimar posição via Wi-Fi + torres
- [x] Ativar mock location com base estimada
- [x] Interface simples com botão "Ativar Aymoré"
- [ ] Visualizar posição em mapa (Google Maps SDK ou MapLibre)
- [ ] Feedback visual: “localização real” vs “simulada”

---

## 🧰 Tecnologias

- Android SDK 33+
- Kotlin (preferencialmente)
- Sensores Android: `SensorManager`, `WifiManager`, `TelephonyManager`
- Sistema de Mock Location Provider
- (Opcional) Google Maps SDK / MapLibre
