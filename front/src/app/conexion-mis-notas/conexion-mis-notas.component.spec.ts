import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConexionMisNotasComponent } from './conexion-mis-notas.component';

describe('ConexionMisNotasComponent', () => {
  let component: ConexionMisNotasComponent;
  let fixture: ComponentFixture<ConexionMisNotasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConexionMisNotasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConexionMisNotasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
