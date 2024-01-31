import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators, ValidatorFn, ValidationErrors, AbstractControl } from '@angular/forms';
import { AuthService } from 'src/app/auth/services/auth.service';
import { TicketsService } from '../../services/tickets.service';
import { TicketRequest } from '../../model/ticket-request';

@Component({
  selector: 'ccf-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.scss']
})
export class BuyComponent {

  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService, private ticketsService: TicketsService){
    route.queryParamMap.subscribe(params => this.buyForm.controls.type.setValue(params.get("type")))
    this.buyForm.addValidators((control:AbstractControl) : ValidationErrors | null => {
        if(Date.parse(this.buyForm.controls.startDate.value || "") < Date.parse(this.buyForm.controls.endDate.value || "")) return null
        else{
          console.log("chronology")
          return {dateChronology:true}
        }
    })
  }

  formattedDuration: string = "15m"
  type: "SEASON"|"SINGLE"|"TIMED" = "SINGLE"

  buyForm = new FormGroup({
    type: new FormControl('',Validators.required),
    fare: new FormControl('"FULL"',Validators.required),
    duration: new FormControl(15),
    startDate: new FormControl((new Date()).toISOString(),[Validators.required]),
    endDate: new FormControl(this.nextMonth().toISOString(),[Validators.required])
  });

  durationChanged(n: number){
    this.formattedDuration = this.formatDuration(n)
  }
  formatDuration(n: number){
    let h=Math.trunc(n/60)
    let m=n%60
    return `${h>0?h+"h":""}${m>0?m+"m":""}`
  }
  changeType(type: "SEASON"|"SINGLE"|"TIMED"){
    this.router.navigate([],{queryParams: {type: type}, relativeTo: this.route})
    this.type = type
  }
  private nextMonth(){
    let dt = new Date()
    dt.setMonth(dt.getMonth()+1)
    return dt
  }

  onSubmit(){
    let data = this.buyForm.value
    if(data.fare && data.type && this.authService.user?.userId){
      let FARE = data.fare.toUpperCase()
      let TYPE = data.type.toUpperCase()
      if((FARE == "FULL" || FARE == "HALF") && (TYPE == "SINGLE" || TYPE == "TIMED" || TYPE=="SEASON")){
        let request: TicketRequest = {
          type: TYPE,
          fare: FARE,
          passengerId: this.authService.user.userId,
          start: data.startDate?new Date(data.startDate):undefined,
          end: data.endDate?new Date(data.endDate):undefined,
          duration: data.duration?data.duration:undefined
        }
        this.ticketsService.buy(request).subscribe(res => {
          this.router.navigate(["/tickets"])
        })
      }
      else console.log("invalid fare/type value")
    }
    else console.log("missing form data from buy form")
  }
}