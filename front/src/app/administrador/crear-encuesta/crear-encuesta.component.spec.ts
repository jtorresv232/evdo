import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearEncuestaComponent } from './crear-encuesta.component';

describe('CrearEncuestaComponent', () => {
  let component: CrearEncuestaComponent;
  let fixture: ComponentFixture<CrearEncuestaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrearEncuestaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrearEncuestaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
