import { NgModule } from '@angular/core';
import { TicketsModule } from './tickets/tickets.module';
import { AuthModule } from './auth/auth.module';
import { RouterModule, Routes } from '@angular/router';
import { TicketResolver } from './tickets/resolvers/ticket.resolver';
import { TicketsListComponent } from './tickets/components/ticketslist/ticketslist.component';
import { BuyComponent } from './tickets/components/buy/buy.component';
import { LoginComponent } from './auth/components/login/login.component';
import { customerGuard } from './auth/guards/customer.guard';
import { controllerGuard } from './auth/guards/controller.guard';
import { CheckComponent } from './tickets/components/check/check.component';
import { RegisterComponent } from './auth/components/register/register.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/tickets'
  },
  {
    path: 'tickets',
    component: TicketsListComponent,
    canActivate: [customerGuard],
    resolve: {
      tickets: TicketResolver
    }
  },
  {
    path: 'buy',
    component: BuyComponent,
    canActivate: [customerGuard]
  },
  {
    path: 'check',
    component: CheckComponent,
    canActivate: [controllerGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes), TicketsModule, AuthModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
