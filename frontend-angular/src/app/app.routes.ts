import { Routes } from '@angular/router';
import { SkeletonComponent } from './layout/skeleton/skeleton.component';
import { SearchComponent } from './components/search/search.component';
import { CardDetailsComponent } from './components/card-details/card-details.component';

export const routes: Routes = [
    {
        path: '',
        component: SkeletonComponent,
        children: [
            { path: '', component: SearchComponent },
            { path: 'product/:idproduct', component: CardDetailsComponent }
        ]
    }
];