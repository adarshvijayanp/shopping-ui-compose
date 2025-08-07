# Shopping UI Compose

A modern, clean Point of Sale (POS) and Shopping cart UI built using **Jetpack Compose**, structured with ViewModel and modular components.  
This project demonstrates:

- A 3-panel landscape POS split screen layout
- Draggable product details bottom sheet with configuration options
- Product list, hold orders/cart summary, and quick action boxes
- State management using ViewModel

## Architecture

- **Jetpack Compose**: Modern declarative UI
- **ViewModel**: State handling for screen logic, product, and modifiers
- **Kotlin**: 100%
- **Clean Modular Structure**:
    - `/model` - data classes (Product, ModifierOption, CartProduct)
    - `/viewmodel` - state & business logic
    - `/ui/components` - reusable UI parts 
    - `/ui/screens` - full-screen/layout composable

## Getting Started

1. **Clone this repository:**

2. **Open with Android Studio (Giraffe or newer recommended).**

3. **Run the app** (ensure your emulator/device is set to landscape for the POS UI).

4. **Try it out:**
    - Click products to open detail/configure panel
    - Adjust colors, models, storage, and modifiers



**Happy Composing!**

