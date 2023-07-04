import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignUpBankInfoComponent } from './sign-up-bank-info.component';

describe('SignUpBankInfoComponent', () => {
  let component: SignUpBankInfoComponent;
  let fixture: ComponentFixture<SignUpBankInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignUpBankInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SignUpBankInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
