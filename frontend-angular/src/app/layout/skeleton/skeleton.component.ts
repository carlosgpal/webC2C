import { Component } from '@angular/core';
import { NavigationComponent } from '../navigation/navigation.component';
import { RouterOutlet } from '@angular/router';
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-skeleton',
  standalone: true,
  imports: [NavigationComponent, FooterComponent, RouterOutlet],
  templateUrl: './skeleton.component.html',
  styleUrl: './skeleton.component.css'
})
export class SkeletonComponent {

}
