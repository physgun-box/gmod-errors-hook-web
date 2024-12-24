<template>
  <div class="flex flex-col w-full cursor-pointer border border-zinc-700 p-3 rounded-md">
    <p class="text-sm text-zinc-500">Hash: {{ hash }}</p>
    <p class="text-sm line-clamp-2 break-all">{{ error }}</p>
    <div class="flex flex-row items-center gap-2 divide-x divide-zinc-700">
      <p class="text-sm">
        <span class="text-zinc-400">os: </span>
        <span v-if="os == 'windows'">windows</span>
        <span v-else-if="os == 'linux'">linux</span>
        <span v-else-if="os == 'osx'">macos</span>
        <span v-else >other</span>
      </p>
      <p class="text-sm ps-2">
        <span class="text-zinc-400">realm: </span>
        <span v-if="realm == 'server'">server</span>
        <span v-else >client</span>
      </p>
      <p class="text-sm ps-2">
        <span class="text-zinc-400">garry's mod version: </span>
        <span>{{ gmv }}</span>
      </p>
    </div>
    <div class="border border-zinc-700 p-2 rounded-md bg-zinc-950 mt-2 mb-2">
      <pre class="text-zinc-400">{{stack}}</pre>
    </div>
  </div>
</template>
<script setup lang="ts">
defineProps<{
  os: string;
  realm: string;
  gmv: string;
  error: string;
  hash: string;
  stack: string;
  active: boolean;
}>();
</script>