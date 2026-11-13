# EJERCICIOS_UT3 · Activities, Intents y Layouts

Boletín de ejercicios evaluables de la **UT3** del módulo **PMDM (Programación Multimedia y Dispositivos Móviles)**, 2º CFGS DAM, curso 2026-2027.

- **Autor:** Elías Ponce Casín
- **Centro:** SAFA Nuestra Señora de los Reyes
- **Tecnología:** Android Studio · Kotlin · Layouts XML (Views)

## Estructura de la app

La app arranca en `MainActivity`, un menú con 6 botones. Cada botón abre la activity de un ejercicio mediante un **Intent explícito**. Para volver al menú se usa `finish()` o el botón de atrás.

```
MainActivity (menú)
├── Ejercicio1Activity           → Validar y abrir una URL
├── Ejercicio2Activity           → Navegación retrasada 10 s
│   └── Ejercicio2DestinoActivity
├── Ejercicio3Activity           → LinearLayout anidados
│   ├── Ejercicio3VerdeActivity
│   ├── Ejercicio3TableroActivity
│   └── Ejercicio3MixtoActivity
├── Ejercicio4Activity           → FrameLayout con 9 FrameLayout
├── Ejercicio5Activity           → Calculadora con TableLayout
└── Ejercicio6Activity           → Perfil con ConstraintLayout
```

## Ejercicios

### Ejercicio 1 · Campo de texto con validación de URL (1,5 pts)
Un `EditText` y un botón. Al pulsarlo se comprueba que el texto no esté vacío y que sea una URL válida con `Patterns.WEB_URL`. Si no lo es, se muestra un `Toast`. Si es válida, se añade `https://` cuando falta y se abre con un **Intent implícito** (`Intent.ACTION_VIEW`), capturando `ActivityNotFoundException` por si no hay ninguna app que pueda abrirla.

**Archivos:** `Ejercicio1Activity.kt` · `activity_ejercicio1.xml`

### Ejercicio 2 · Navegación retrasada entre activities (1 pt)
El botón no navega al momento: crea un `Intent` hacia `Ejercicio2DestinoActivity`, lo envuelve en un `PendingIntent` (`FLAG_IMMUTABLE`) y un `Handler` asociado al hilo principal lo lanza con `postDelayed` a los **10 segundos** mediante `send()`. En `onDestroy` se cancela con `removeCallbacksAndMessages(null)` para que no se abra si el usuario sale antes.

**Archivos:** `Ejercicio2Activity.kt` · `Ejercicio2DestinoActivity.kt` · `activity_ejercicio2.xml` · `activity_ejercicio2_destino.xml`

### Ejercicio 3 · Pantallas con LinearLayout anidados (1,5 pts)
Los cuatro diseños del enunciado, hechos solo con `LinearLayout`, `orientation` y `layout_weight` (con `0dp` en la dirección del reparto):

| Pantalla | Diseño | Estructura |
|---|---|---|
| Principal | azul · blanco · azul | Fila horizontal con pesos 1-1-1. La franja central es un LinearLayout vertical con los 3 botones |
| Verde | verde · blanco · verde | Fila horizontal con pesos 1-4-1 |
| Tablero | cuadrícula 2×3 rojo/blanco | LinearLayout vertical con 2 filas horizontales de 3 casillas |
| Mixto | columnas arriba, franjas abajo | Mitad superior horizontal y mitad inferior vertical |

Los tres botones se asocian a su activity con un `mapOf` recorrido con `forEach`. Cada pantalla muestra su mensaje propio con un `Toast` en `onCreate`.

**Archivos:** `Ejercicio3*Activity.kt` · `activity_ejercicio3*.xml`

### Ejercicio 4 · FrameLayout con nueve FrameLayout (1 pt)
Un `FrameLayout` raíz con 9 `FrameLayout` de 100dp, cada uno de un color. Como en un FrameLayout los hijos se apilan, cada uno se coloca en su posición de la cuadrícula 3×3 con `layout_gravity` (`top|start`, `center`, `bottom|end`...).

**Archivos:** `Ejercicio4Activity.kt` · `activity_ejercicio4.xml`

### Ejercicio 5 · Calculadora con TableLayout (2 pts)
`TableLayout` con `stretchColumns="*"`, una fila por línea de botones y `layout_span="4"` para la pantalla y el botón `=`. Incluye cifras 0-9, separador decimal, `C` para reiniciar, `=` y las operaciones `+ - * /`.

La lógica guarda el estado en tres variables (`operando1`, `operador`, `empezarNumeroNuevo`):
- Solo se permite un punto decimal por número.
- Las operaciones encadenadas se resuelven en orden, como en una calculadora de bolsillo (`2 + 3 * 4 = 20`).
- La división por cero muestra `Error`.
- Los resultados enteros se muestran sin `.0`.

**Archivos:** `Ejercicio5Activity.kt` · `activity_ejercicio5.xml`

### Ejercicio 6 · Pantalla de perfil con ConstraintLayout (1,5 pts)
Imagen de perfil, nombre, rol y descripción encadenados verticalmente con restricciones (`layout_constraintTop_toBottomOf`) y centrados atando los dos lados al padre. Los botones *Seguir* y *Mensaje* forman una cadena horizontal y se reparten el ancho; *Volver* queda anclado abajo.

**Archivos:** `Ejercicio6Activity.kt` · `activity_ejercicio6.xml`

## Cómo ejecutarlo

1. Clonar el repositorio y abrirlo con Android Studio.
2. Esperar a que termine la sincronización de Gradle.
3. Seleccionar la configuración **app** y pulsar ▶ en un emulador o dispositivo.
