package com.klu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class LedController {
	private static GpioPinDigitalOutput pin;
	@RequestMapping("/")
	public String greeting() {
		return "Hello World!!!";
	}
	
	@RequestMapping("/toggle")
	public String light() {
		getPin().toggle();
		return "OK";
	}
	
	private String checkState() {
		return (getPin().isHigh() ? "Light is ON" :"Light is OFF");
	}
	
	@RequestMapping("/on")
	public String on() {
		getPin().high();
		return checkState();
	}
	
	@RequestMapping("/on")
	public String off() {
		getPin().low();
		return checkState();
	}
	
	@RequestMapping("/blink")
	public String blink() {
		getPin().blink(200L,5000L);
		return "Light is blinking...";
	}
	
	@RequestMapping("/pulse")
	public String pulse() {
		getPin().pulse(5000L);
		return "Light is pulsing...";
	}
	private GpioPinDigitalOutput getPin() {
		if(pin == null) {
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
		}
		return pin;
	}
}