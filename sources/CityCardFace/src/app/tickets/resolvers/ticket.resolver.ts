import { Injectable } from '@angular/core';
import { Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Ticket } from '../model/ticket';
import { TicketsService } from '../services/tickets.service';

@Injectable({
  providedIn: 'root'
})
export class TicketResolver  {
  
  constructor(private readonly ticketsService: TicketsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Ticket[]> {
    return this.ticketsService.getAll();
  }
}
