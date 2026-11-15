# CaféPunto – Tarea 2 (PMyDM)

App Android con una única activity (`MainActivity` + `FragmentContainerView`) y navegación con `nav_graph`.

## Estructura (MVVM)

```
com.dam.cafepunto
├── MainActivity.kt            Activity única; solo Registro permite girar la pantalla
├── data/                      Modelo
│   ├── UsuarioRepositorio.kt  Usuario de prueba para el login: demo@cafepunto.com / 123456
│   └── Validador.kt           Funciones de validación de campos
└── ui/                        Vista + ViewModel
    ├── inicio/                InicioFragment
    ├── login/                 LoginFragment, LoginViewModel, LoginUiState, LoginUiEvent
    ├── registro/              RegistroFragment, RegistroViewModel, RegistroUiState, RegistroUiEvent
    ├── recuperar/             RecuperarFragment, RecuperarViewModel, RecuperarUiState, RecuperarUiEvent
    └── home/                  HomeFragment
```

- Cada fragment envía eventos (`UiEvent`) al ViewModel con `doOnTextChanged` y observa el `StateFlow<UiState>`.
- Los botones de login, registro y recuperar contraseña solo se habilitan cuando los campos son correctos.
- Los errores de cada campo se muestran con la propiedad `error` de `TextInputLayout`; los errores de acción con `Toast`.

## Orientación

`res/layout/fragment_registro.xml` (vertical) y `res/layout-land/fragment_registro.xml` (horizontal). Los datos
escritos se mantienen al girar porque el estado vive en `RegistroViewModel`.

## Idioma

`res/values/strings.xml` (español) y `res/values-en/strings.xml` (inglés). La app usa el idioma del dispositivo.
