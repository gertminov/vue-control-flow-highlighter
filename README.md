# Vue Template highlighter
this is a plugin for jetbrains IDEs (webstorm, intellij) which highlights vue.js control flow blocks.
which makes loops and conditional rendering easier to spot in the template.
Vue templates have the strange quirk, that they put the control flow on the element they affect. you can get around that by doing

```vue
<template v-if="expression" />
  <your-element/>
</template>
```

this plugin highlights all `<template>` tags which have control flow attributes like `v-if`, `v-for` (anything starting with `v-`) on them.
