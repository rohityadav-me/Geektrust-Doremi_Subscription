package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.exceptions.NoSuchCommand;

public class App {

	public static void main(String[] args) {
		int inputFileLocation =  0;
		BufferedReader reader;
		String inputFile = args[inputFileLocation];
		CommandInvoker invoker = new CommandInvoker();
		try{
			reader = new BufferedReader(new FileReader(inputFile));
			String line = reader.readLine();
			while(line != null){
				List<String> inputCommands = Arrays.asList(line.split(" "));
				invoker.execute(inputCommands);
				line = reader.readLine();
			}
			reader.close();
		}catch(IOException | NoSuchCommand e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
