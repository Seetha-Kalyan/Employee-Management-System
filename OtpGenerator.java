package test;

import java.util.Random;

public class OtpGenerator {
    public int otp;

	public int otpGen(){
        Random rand = new Random();
         otp = rand.nextInt(9000) + 1000;
        System.out.println("Your OTP is: " + otp);
		return otp;
    }
}