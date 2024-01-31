import { ResolveFn } from '@angular/router';
import { CheckService } from '../services/check.service';
import { inject } from '@angular/core';

export const vehicleResolver: ResolveFn<string[]> = (route, state) => {
  const checkService = inject(CheckService)
  return checkService.vehicles();
};
