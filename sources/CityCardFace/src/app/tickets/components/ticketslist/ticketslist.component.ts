import { Component } from '@angular/core';
import { Ticket } from '../../model/ticket';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'ccf-ticketslist',
  templateUrl: './ticketslist.component.html',
  styleUrls: ['./ticketslist.component.scss']
})
export class TicketsListComponent {
  constructor(private route: ActivatedRoute, private router: Router){
    this.tickets = route.snapshot.data['tickets'];
    if(!this.tickets) this.tickets = []
    route.queryParamMap.subscribe(params => {
      let t = params.get("type")
      if(t == "SEASON" || t == "SINGLE" || t=="TIMED") this.filter = t
      else this.filter = "all"
      this.filteredTickets = this.visibleTickets()
    })
  }
  filter: "all"|"SEASON"|"SINGLE"|"TIMED" = "all"
  tickets: Ticket[] = []
  filteredTickets = this.visibleTickets()

  visibleTickets() {
    if(this.filter=="all"){
      return this.tickets;
    }
    else{
      return this.tickets.filter(t => t.type == this.filter)
    }
  }

  changeFilter(filter: "all"|"SEASON"|"SINGLE"|"TIMED"){
    this.router.navigate([],{queryParams: {type: filter}, relativeTo: this.route})
    this.filter = filter
    this.filteredTickets = this.visibleTickets()
  }
}