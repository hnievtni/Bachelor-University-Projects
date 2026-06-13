Title:
  Designing the control unit in the UGI car robot with the ability to detect obstacles and indirect movement
assumptions:
In the Proteus environment, there is an ATMega32 microcontroller with a keyboard, two motors with the ability to control rotation in two directions, and an ultrasonic sensor. The two motors in the circuit are supposed to simulate the movement of a car, whose wheels are actually connected to the motors in this simulator. The car must be able to move in a straight path and also turn in both left and right directions. Maximum speed
You can choose the movement as you like.
  Required programs:
a) Write a program in assembly language that allows the machine to move directly. for this
The speed of rotation of both engines should be the same.
b) Write a program in assembly language to determine in which direction the car should move using the four-key keyboard (left, right, straight, backward). In this section, you can use the LED to display the detected movement direction, and there is no need to transfer the command to the car.

c) Transfer the directions determined in the previous section to the motors so that the car moves in the same direction as long as the relevant keys are held in a certain direction (in fact, the car can be controlled by the keys) Suppose that the keyboard is connected wirelessly to the UGI
Assembly).
An ultrasonic sensor is placed in front of the UGI and prevents it from hitting obstacles. a program
Write that if there is an obstacle, even by pressing the keys, it does not move in that particular direction.