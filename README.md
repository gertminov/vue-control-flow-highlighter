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

| With plugin | Without plugin |
| --- | --- |
| <img width="423" height="201" alt="image (5)" src="https://github.com/user-attachments/assets/ac00f920-1ac7-4408-8796-eeea2f60c5d6" /> | <img width="428" height="207" alt="image (4)" src="https://github.com/user-attachments/assets/5424fec1-13b0-4b84-9906-c9ac846b58c0" /> |
