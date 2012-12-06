package exceed.air.controller;

import exceedvote.air.model.Register;

/**
 * Mediator Register and RegistorUI
 * @author AirTeam
 */
public class ControllerRegister {
	private static ControllerRegister controllerRegister;
	private Register register;
	
		private ControllerRegister(){
			register = new Register();
		}
		

		/**
		 * ControllerRegister is the singleton pattern 
		 * @return only one ControllerRegister
		 */
		public static ControllerRegister getInstance(){
			if(controllerRegister==null) controllerRegister = new ControllerRegister();
			return controllerRegister;
		}
		
		/**
		 * Regist the user in register system
		 * @param name is identity name of that user
		 * @param pass is security code of that user
		 * @param type (Student,Teacher)
		 * @return true if regist successfully
		 */
		public boolean addUser(String name,String pass,String type){
			boolean make = register.addVoter(name,pass,type);
			return make;
		}
		
}
