import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'ccf-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  constructor(private readonly authService: AuthService) { }

  registerForm = new FormGroup({
    login: new FormControl('',[Validators.required]),
    password: new FormControl('',[Validators.required]),
    email: new FormControl('',[Validators.required]),
  });

  onSubmit(){
    let data = this.registerForm.value
    if(data.email && data.login && data.password){
      this.authService.register({login: data.login, password: data.password, email: data.email})
    }
    else console.log("Missing data from register form")
  }
}
