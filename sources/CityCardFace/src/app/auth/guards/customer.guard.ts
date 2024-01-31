import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

import { inject } from '@angular/core';

export const customerGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService)
  if(authService.isCustomer()) return true;
  else{
    const router = inject(Router)
    return router.parseUrl('/login')
  }
};
