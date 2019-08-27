import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpEditAsignacionComponent } from './pop-up-edit-asignacion.component';

describe('PopUpEditAsignacionComponent', () => {
  let component: PopUpEditAsignacionComponent;
  let fixture: ComponentFixture<PopUpEditAsignacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopUpEditAsignacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopUpEditAsignacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
