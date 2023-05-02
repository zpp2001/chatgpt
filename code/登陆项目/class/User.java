package 登陆项目.;

public class User{
      -username: String;
      -password: String;
      +User(username: String, password: String);
      +getUsername(): String;
      +getPassword(): String;
}