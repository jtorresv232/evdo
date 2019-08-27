import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpAddPreguntaComponent } from './pop-up-add-pregunta.component';

describe('PopUpAddPreguntaComponent', () => {
  let component: PopUpAddPreguntaComponent;
  let fixture: ComponentFixture<PopUpAddPreguntaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopUpAddPreguntaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopUpAddPreguntaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
