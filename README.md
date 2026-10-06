# SauceDemo: Java + Selenium

Proyecto de práctica de automatización web con Java, Maven, Selenium WebDriver y TestNG.

## Requisitos

- JDK 17 o posterior
- Maven 3.8 o posterior
- Google Chrome, Firefox o Microsoft Edge, según el navegador configurado
- Visual Studio Code con **Extension Pack for Java**

`DriverHelper` usa Selenium Manager (incluido en Selenium 4) para resolver y descargar el driver compatible con el navegador instalado, omitiendo drivers incompatibles encontrados en `PATH`. No es necesario instalar ChromeDriver manualmente. Configura el navegador, las credenciales de prueba y las URLs en `configuration.properties`, junto a `.gitignore`.

## Ejecutar la suite de regresión

Desde la carpeta del proyecto:

```bash
mvn test
```

Maven ejecuta la suite TestNG definida en `runners/Regression.xml`. La prueba de inicio de sesión valida que se muestre la página de productos en [SauceDemo](https://www.saucedemo.com/).

## Estructura

```text
.gitignore
configuration.properties
runners/
└── Regression.xml
src/
└── test/
    └── java/
        └── com/practice/saucedemo/
            ├── pages/
            │   ├── InventoryPage.java
            │   └── LoginPage.java
            ├── test/
            │   ├── LoginTest.java
            │   └── TestBase.java
            └── utils/
                ├── BrowserUtils.java
                ├── ConfigReader.java
                └── DriverHelper.java
```

Las clases de `pages` encapsulan los localizadores y las interacciones de cada página siguiendo Page Object Model. `TestBase` configura y cierra el driver para las pruebas de `test`; `runners` contiene el XML de la suite TestNG y `utils` centraliza las esperas del navegador, la lectura de configuración y las funciones del driver. `configuration.properties` contiene `login.username`, `login.password`, `pages.login.url`, `browser` y `explicit.wait.seconds`.
