# Usage examples

<pre><code>
Spinner spinner = new Spinner(SpinnerType.ROTATING_PLANE);
spinner.setSize(SpinnerSize.SM);
addComponent(spinner);
  
Spinner spinner2 = new Spinner(SpinnerType.CIRCLE).large();
addComponent(spinner2);

Spinner spinner3 = new Spinner(SpinnerType.FOLDING_CUBE)
    .small().withStyle("green");
addComponent(spinner3);
</code></pre>