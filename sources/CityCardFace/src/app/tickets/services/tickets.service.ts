import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Ticket } from '../model/ticket';
import { TicketRequest } from '../model/ticket-request';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Router } from '@angular/router';
import { TicketDto } from '../model/ticket-dto';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  constructor(private readonly http: HttpClient, private readonly router: Router, private readonly authService: AuthService) { }

  ticketDtoToTicket(dto: TicketDto):Ticket{
    return {id:dto.id, type: dto.type, fare: dto.fare, vehicleId: dto.vehicleId, timestamp: dto.timestamp, duration:dto.duration, start: dto.start?new Date(dto.start):undefined, end: dto.end?new Date(dto.end):undefined}
  }

  getAll(): Observable<Ticket[]> {
    return this.http.get<TicketDto[]>(`${environment.backendURL}/tickets/${this.authService.user?.userId}`).pipe(catchError(err => {
      return of([])
    })).pipe(map(tdtos => tdtos.map(dto => this.ticketDtoToTicket(dto))));
  }

  buy(tr: TicketRequest): Observable<Ticket> {
    return this.http.post<TicketDto>(`${environment.backendURL}/buy`,tr).pipe(map(dto => this.ticketDtoToTicket(dto)));
  }

}
