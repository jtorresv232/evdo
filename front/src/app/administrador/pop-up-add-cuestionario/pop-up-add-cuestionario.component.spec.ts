import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpAddCuestionarioComponent } from './pop-up-add-cuestionario.component';

describe('PopUpAddCuestionarioComponent', () => {
  let component: PopUpAddCuestionarioComponent;
  let fixture: ComponentFixture<PopUpAddCuestionarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopUpAddCuestionarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopUpAddCuestionarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
