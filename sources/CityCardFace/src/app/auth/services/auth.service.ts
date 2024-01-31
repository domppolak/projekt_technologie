import { HttpClient, HttpEvent, HttpEventType, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { inject } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';
import { Router } from '@angular/router';
import { catchError, last, map, of, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private readonly http: HttpClient, private readonly router: Router) { }

  user?:User

  isCustomer():boolean{
    return this.user?.roles.includes("PASSSENGER") || false
  }
  isController(){
    return this.user?.roles.includes("CONTROLLER") || false
  }
  getToken(){
    return ""
  }

  private headers = {headers: {'Content-Type': 'application/json'}, responseType:'json' as const};

  private getEventMessage(event: HttpEvent<any>, file: {}) {
    switch (event.type) {
      case HttpEventType.Sent:
        return `sent.`;
  
      case HttpEventType.UploadProgress:
        // Compute and show the % done:
        const percentDone = event.total ? Math.round(100 * event.loaded / event.total) : 0;
        return `File is ${percentDone}% uploaded.`;
  
      case HttpEventType.Response:
        return `File was completely uploaded!`;
  
      default:
        return `File surprising upload event: ${event.type}.`;
    }
  }

  login(loginData: {login:string, password:string}){
    this.http.post<User>(`${environment.backendURL}/login`, JSON.stringify(loginData), this.headers).pipe(catchError((err, caught) =>{
      console.log(err)
      return of({userId:"-1",roles:["customer"]})
    })).subscribe(user =>{
      this.user = user
      this.router.navigate(this.isController()?["/check"]:["/tickets"])
    });
  }

  register(registerData: {login:string, password:string, email:string}){
    this.http.post<User>(`${environment.backendURL}/register`, JSON.stringify(registerData), this.headers).subscribe(user =>{
      this.user = user
      this.router.navigate(["/tickets"])
    });
    
  }
}

export function getToken(){
    const auths = inject(AuthService)
    return auths.getToken()
}