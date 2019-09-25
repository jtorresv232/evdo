import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpAddEvaluacionComponent } from './pop-up-add-evaluacion.component';

describe('PopUpAddEvaluacionComponent', () => {
  let component: PopUpAddEvaluacionComponent;
  let fixture: ComponentFixture<PopUpAddEvaluacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopUpAddEvaluacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopUpAddEvaluacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
