import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Ticket } from '../model/ticket';

@Injectable({
  providedIn: 'root'
})
export class CheckService {

  constructor(private readonly http: HttpClient) { }

  vehicles(): Observable<string[]> {
    return this.http.get<string[]>(`${environment.backendURL}/getAllVehicles`);
  }

  check(ticketId: string, vehicleId: string): Observable<boolean> {
    let now = new Date()
    return this.http.get<boolean>(`${environment.backendURL}/check`,{params: {
      ticketId: ticketId,
      vehicleId: vehicleId,
      now: `${now.toISOString().substring(0,23)}`
    }});
  }
}
