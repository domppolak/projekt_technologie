import { Component, Input } from '@angular/core';
import { Ticket } from '../../model/ticket';

@Component({
  selector: 'ccf-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss']
})
export class TicketComponent {

  @Input() ticket!: Ticket;

  isValid():boolean{
    let now = new Date()
    switch(this.ticket.type){
      case "SEASON":
        return this.ticket.start != null && this.ticket.start < now && this.ticket.end != null && this.ticket.end > now
      case "SINGLE":
        return false;
      case "TIMED":
        return this.ticket.start != null && this.ticket.end != null && this.ticket.end > now
    }
  }

  isInvalid():boolean{
    let now = new Date()
    switch(this.ticket.type){
      case "SEASON":
        return this.ticket.start == null || this.ticket.end == null || this.ticket.end < now
      case "SINGLE":
        now.setDate(now.getDate()-1)
        return this.ticket.timestamp != null && this.ticket.timestamp < now;
      case "TIMED":
        return this.ticket.start != null && this.ticket.end != null && this.ticket.end < now
    }
  }

  isValidatedSingleTicket():boolean{
    return this.ticket.type == "SINGLE" && this.ticket.vehicleId != null
  }

  isNotYetValidated():boolean{
    return (this.ticket.type == "SINGLE" && this.ticket.vehicleId == null) || (this.ticket.type == "TIMED" && this.ticket.start == null)
  }

  formatDuration(n: number){
    let h=Math.trunc(n/60)
    let m=n%60
    return `${h>0?h+"h":""}${m>0?m+"m":""}`
  }

}
