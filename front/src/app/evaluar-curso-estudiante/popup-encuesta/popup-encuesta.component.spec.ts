import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupEncuestaComponent } from './popup-encuesta.component';

describe('PopupEncuestaComponent', () => {
  let component: PopupEncuestaComponent;
  let fixture: ComponentFixture<PopupEncuestaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopupEncuestaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupEncuestaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
