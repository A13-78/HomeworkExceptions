package org.example;

import exception.WrongLoginException;
import exception.WrongPasswordException;
/*
1. Напишите статический метод, который принимает на вход три параметра: login, password и confirmPassword.
2. Параметр Login содержит в себе только латинские буквы, цифры и знак подчеркивания.
   *Например, java_skypro.go*
     a. У параметра login есть ограничение по длине – он должен быть равен или меньше 20 символов.
        Если login длиннее 20 символов, то  необходимо выбросить исключение – `WrongLoginException`.
3. Параметр Password содержит в себе только латинские буквы, цифры и знак подчеркивания.
   *Например, D_1hWiKjjP_9*
4. У параметра password тоже есть ограничение по длине – он должен быть строго меньше 20 символов. 
5. Параметры password и confirmPassword должны быть равны. Если это требование не соблюдается,
   то нужно выбросить `WrongPasswordException`.
6. Обработка исключений проводится внутри метода.
7. Для обработки исключений используйте multi-catch block.
 */
public class Main {
    public static void main(String[] args) {
        String login = "123www";
        String pass = "www_123";
        String confirmPass = "www_123";
        System.out.println(acceptThreeParameters(login, pass, confirmPass));
    }

    public static boolean acceptThreeParameters(String login, String password, String confirmPassword) {
        boolean checkLogin;
        boolean checkPassword;
        boolean checkLengthPassword;
        boolean checkLengthLogin;
        boolean checkPasswordMatching;
        try {
            checkLogin = checkValidationCharacter(login);
            checkPassword = checkValidationCharacter(password);
            checkLengthLogin = checkLengthLogin(login);
            checkLengthPassword = checkLengthPassword(password);
            checkPasswordMatching = checkPasswordMatching(password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return checkLogin && checkPassword && checkLengthPassword && checkLengthLogin && checkPasswordMatching;
    }

    public static boolean checkValidationCharacter(String checkWord) {
        if (checkWord == null) {
            throw new NullPointerException("Введена пустая строка");
        }
        if (checkWord.matches("\\w+")) {
            return true;
        }
        System.out.printf("Недопустимые символы в слове! - %s", checkWord);
        return false;
    }

    public static boolean checkLengthLogin(String login) throws WrongLoginException {
        if (login.length() >= 20) {
            throw new WrongLoginException("Слишком большой логин");
        }
        return true;
    }

    public static boolean checkLengthPassword(String pass) {
        if (pass.length() > 20) {
            System.out.println("Слишком длинный пароль");
            return false;
        }
        return true;
    }

    public static boolean checkPasswordMatching(String password, String confirmPassword) throws WrongPasswordException {
        if (password.equals(confirmPassword)) return true;
        throw new WrongPasswordException("Пароли не совпадают");

    }
}