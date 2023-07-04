import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignUpSharedServiceComponent } from './sign-up-shared-service.component';

describe('SignUpSharedServiceComponent', () => {
  let component: SignUpSharedServiceComponent;
  let fixture: ComponentFixture<SignUpSharedServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignUpSharedServiceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SignUpSharedServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
