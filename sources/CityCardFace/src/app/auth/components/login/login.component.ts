import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'ccf-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  constructor(private readonly authService: AuthService) { }

  loginForm = new FormGroup({
    login: new FormControl('',[Validators.required]),
    password: new FormControl('',[Validators.required]),
  });
  
  onSubmit(){
    let data = this.loginForm.value
    if(data.login && data.password){
      this.authService.login({login: data.login, password: data.password})
    }
    else console.log("Missing data from login form")
  }
}
