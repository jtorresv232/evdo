import { TestBed, inject } from '@angular/core/testing';

import { DerivadasService } from './derivadas.service';

describe('DerivadasService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DerivadasService]
    });
  });

  it('should be created', inject([DerivadasService], (service: DerivadasService) => {
    expect(service).toBeTruthy();
  }));
});
