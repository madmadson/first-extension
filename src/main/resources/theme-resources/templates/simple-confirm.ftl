<#import "template.ftl" as layout>
<@layout.registrationLayout >

    <form action="${url.loginAction}" method="post">

        <div class="form-group">
            <label>
                <input type="checkbox" name="confirm"> Ich bestätige, dass ich die Bedingungen gelesen habe.
            </label>
        </div>

        <div class="form-group">
            <button class="btn btn-primary" type="submit">Bestätigen</button>
        </div>

    </form>

</@layout.registrationLayout>