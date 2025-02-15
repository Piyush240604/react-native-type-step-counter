import { NativeModules, NativeEventEmitter, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-type-step-counter' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const { TypeStepCounter } = NativeModules;

if (!TypeStepCounter) {
  throw new Error(LINKING_ERROR);
}

const stepCounterEmitter = new NativeEventEmitter(TypeStepCounter);

/**
 * Start step counter listener
 * @param callback - function that receives the step count updates
 * @returns function to stop listening
 */
export const startStepCounter = (callback: (steps: number) => void) => {
  TypeStepCounter.startStepCounter();
  const subscription = stepCounterEmitter.addListener('StepCounterUpdate', callback);

  return () => {
    subscription.remove();
    TypeStepCounter.stopStepCounter();
  };
};

/**
 * Stop step counter manually (if needed)
 */
export const stopStepCounter = () => {
  TypeStepCounter.stopStepCounter();
};
