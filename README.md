# react-native-type-step-counter

A lightweight React Native library to access the `Sensor.TYPE_STEP_COUNTER` on Android devices, without using `TYPE_ACCELEROMETER`. This library provides an easy-to-use interface to track step counts in your React Native application.

## Features
- Utilizes `Sensor.TYPE_STEP_COUNTER` for accurate step tracking.
- No usage of `TYPE_ACCELEROMETER` as per design.
- Simple API to start and stop step counting.
- Lightweight and optimized for React Native.

## Installation

```bash
npm install react-native-type-step-counter
```

or

```bash
yarn add react-native-type-step-counter
```

---

## Usage

```js
import StepCounter from 'react-native-type-step-counter';

StepCounter.startStepCounter();

StepCounter.onStepCountChange((steps) => {
  console.log('Steps counted:', steps);
});

StepCounter.stopStepCounter();
```

---

## API

### `startStepCounter()`
Starts the step counter.

### `stopStepCounter()`
Stops the step counter.

### `onStepCountChange(callback)`
Registers a callback function that receives the current step count as a parameter.

---

## Platform Support
- **Android** only.

---

## Contributing
Contributions are welcome! Feel free to open issues or submit PRs.

---

## License
This project is licensed under the MIT License.

