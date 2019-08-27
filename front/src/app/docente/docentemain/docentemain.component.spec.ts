import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocentemainComponent } from './docentemain.component';

describe('DocentemainComponent', () => {
  let component: DocentemainComponent;
  let fixture: ComponentFixture<DocentemainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocentemainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocentemainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
