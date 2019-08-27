import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltrosAsignacionesComponent } from './filtros-asignaciones.component';

describe('FiltrosAsignacionesComponent', () => {
  let component: FiltrosAsignacionesComponent;
  let fixture: ComponentFixture<FiltrosAsignacionesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiltrosAsignacionesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltrosAsignacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
