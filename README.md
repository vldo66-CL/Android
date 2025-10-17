# PruebaAndroid

Una aplicación de Android de demostración que muestra varias funcionalidades básicas de desarrollo móvil.

## Descripción

PruebaAndroid es una aplicación de prueba desarrollada para demostrar conceptos fundamentales del desarrollo de aplicaciones Android, incluyendo navegación entre actividades, integración con la cámara, envío de correos electrónicos con adjuntos, y apertura de mapas y sitios web.

## Características

- **Navegación principal**: Pantalla principal con botones para acceder a diferentes secciones de la app.
- **Composición de correos**: Permite redactar correos electrónicos, adjuntar archivos desde el dispositivo o capturar fotos con la cámara.
- **Configuraciones**: Pantalla de ajustes (actualmente básica, lista para extensiones futuras).
- **Localización**: Abre Google Maps en la ubicación de Santiago de Chile, o un navegador web si Maps no está disponible.
- **Navegación web**: Abre el sitio web de Santo Tomás en el navegador.

## Permisos requeridos

- **Cámara**: Para capturar fotos y adjuntarlas a correos.
- **Almacenamiento**: Para guardar fotos temporales y acceder a archivos adjuntos.

## Requisitos del sistema

- **Versión mínima de Android**: API 31 (Android 12)
- **Versión objetivo**: API 36 (Android 16)
- **Java**: Versión 11

## Instalación

1. Clona este repositorio:
   ```
   git clone https://github.com/vldo66-CL/Android.git
   ```

2. Abre el proyecto en Android Studio.

3. Sincroniza las dependencias del proyecto.

4. Ejecuta la aplicación en un emulador o dispositivo físico.

## Uso

1. Al iniciar la app, verás la pantalla principal con botones para diferentes funciones.
2. Toca "Correo" para acceder al compositor de correos electrónicos.
3. En la pantalla de correo, puedes:
   - Ingresar destinatario, asunto y mensaje.
   - Adjuntar archivos existentes.
   - Tomar una foto con la cámara.
   - Enviar el correo usando la app de correo instalada en el dispositivo.
4. Usa los otros botones para acceder a ajustes, ubicación GPS o sitio web.

## Estructura del proyecto

- `MainActivity`: Actividad principal con navegación.
- `CorreoActivity`: Compositor de correos con funcionalidad de adjuntos.
- `AjustesActivity`: Pantalla de configuraciones (básica).
- `GPSActivity`: Manejo de ubicación GPS (abre mapas).

## Tecnologías utilizadas

- **Lenguaje**: Java
- **Framework**: Android SDK
- **Dependencias principales**:
  - AppCompat
  - Material Design Components
  - ConstraintLayout
  - FileProvider para manejo seguro de archivos

## Contribución

Este proyecto es una demostración educativa. Para contribuir:

1. Haz un fork del repositorio.
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## Autor

Desarrollado por [vldo66-CL](https://github.com/vldo66-CL)
