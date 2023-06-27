import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovingBackroundComponent } from './moving-backround.component';

describe('MovingBackroundComponent', () => {
  let component: MovingBackroundComponent;
  let fixture: ComponentFixture<MovingBackroundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovingBackroundComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovingBackroundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
