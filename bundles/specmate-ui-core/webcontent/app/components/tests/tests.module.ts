import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TestCaseRow} from './test-case-row.component';
import { NgModule } from '@angular/core';

import { TestsPerspective } from './tests-perspective.component';
//import { TestSpecificationEditor } from './test-specification-editor.component';

import { CoreModule } from '../core/core.module';
import { TestsRoutingModule } from './tests-routing.module';
import { TestSpecificationEditor } from './test-specification-editor.component';
import { TestParameterForm } from './test-parameter-form.component';

@NgModule({
    imports: [
        CoreModule,
        TestsRoutingModule,
        FormsModule,
        ReactiveFormsModule
    ],
    declarations: [
        TestsPerspective,
        TestSpecificationEditor,
        TestCaseRow,
        TestParameterForm
    ],
    providers: [],
    exports: [],
})
export class TestsModule { }