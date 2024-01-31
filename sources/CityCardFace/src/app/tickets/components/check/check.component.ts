import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { CheckService } from '../../services/check.service';

@Component({
  selector: 'ccf-check',
  templateUrl: './check.component.html',
  styleUrls: ['./check.component.scss']
})
export class CheckComponent {

  constructor(private route: ActivatedRoute, private checkService: CheckService){}

  checkForm = new FormGroup({
    ticketId: new FormControl('',Validators.required),
    vehicleId: new FormControl('',Validators.required)
  });
  resultControl = new FormControl('Fill form first');

  onSubmit(){
    let data = this.checkForm.value;
    if(data.ticketId && data.vehicleId){
      this.checkService.check(data.ticketId, data.vehicleId).subscribe(
        (result:boolean) => this.resultControl.setValue(`Valid: ${result}`)
      );
    }
  }
}
