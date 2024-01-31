import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatSliderModule } from '@angular/material/slider';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { TicketsListComponent } from './components/ticketslist/ticketslist.component';
import { ValidateComponent } from './components/validate/validate.component';
import { TicketComponent } from './components/ticket/ticket.component';
import { PluscardComponent } from './components/utils/pluscard/pluscard.component';
import { BuyComponent } from './components/buy/buy.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatNativeDateModule } from '@angular/material/core';
import { CheckComponent } from './components/check/check.component';



@NgModule({
  declarations: [
    TicketsListComponent,
    ValidateComponent,
    TicketComponent,
    PluscardComponent,
    BuyComponent,
    CheckComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatInputModule,
    MatIconModule,
    MatSliderModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule
  ],
  exports: [
    MatCardModule,
    TicketsListComponent
  ]
})
export class TicketsModule { }
