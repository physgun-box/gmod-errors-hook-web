<template>
  <div class="bg-black w-screen max-h-screen h-screen flex flex-col ">
    <div class="flex flex-col w-[70rem] mx-auto flex-1 p-10 max-h-full">
      <img :src="logo" alt="Logo" class="h-10 opacity-20"/>
      <div class="flex flex-row gap-2 items-center">

        <p class="text-white text-xl font-semibold">Logs</p>
      </div>
      <p class="text-zinc-500 mb-5">Error logs from your garry's mod server's</p>

      <!-- Scroll indicator at the top -->
      <div v-show="showScrollTop" id="showif_scrolled_down" class="bg-gradient-to-b from-black to-transparent w-full h-10 -mb-10 z-10"></div>

      <div id="scroller"
           class="flex flex-col flex-1 overflow-y-auto gap-3 pe-2 relative"
           @scroll="handleScroll">

        <div v-if="status == 'pending'" class="flex flex-row items-center justify-center w-full p-5">
          <Icon name="mingcute:loading-fill" class="w-8 h-8 animate-spin"/>
        </div>

        <LogItem v-else v-for="log in data" :key="log.id" :error="log.error" :hash="log.hash" :os="log.os" :gmv="log.gmv" :realm="log.realm" :stack="log.stack" :active="curStack == log.id" @click="curStack = log.id" />

        <!-- Load More Button -->
        <div v-if="hasMoreLogs" class="flex justify-center mt-4">
          <button @click="loadMore" class="text-white bg-blue-500 hover:bg-blue-700 px-4 py-2 rounded-md">
            Load More
          </button>
        </div>
      </div>

      <!-- Scroll indicator at the bottom -->
      <div v-show="showScrollBottom" id="hideonly_if_eached_bottom_scroller" class="bg-gradient-to-t from-black to-transparent w-full h-10 -mt-10 z-10"></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import '~/assets/fonts.css';
import logo from '~/assets/logo.svg';

interface Log {
  id: number
  error: string
  stack: string
  realm: string
  addon: string
  hash: string
  gamemode: string
  gmv: string
  os: string
  ds: boolean
  v: number
  serverTagName: string
}

interface LogResponse {
  content: Log[];
  pageable: {
    pageNumber: number;
    pageSize: number;
  };
  totalPages: number;
  totalElements: number;
  numberOfElements: number;
  last: boolean;
  first: boolean;
}

const currentPage = ref(1); // Current page number
const data = ref<Log[]>([]); // Logs for the current page
const hasMoreLogs = ref(true); // If there are more logs to load
const status = ref<'pending' | 'done'>('done');
const curStack = ref<number | null>(null);

// Scroll state indicators
const showScrollTop = ref(false);
const showScrollBottom = ref(false);

// Load logs from the server
const loadLogs = async (page: number) => {
  status.value = 'pending';
  try {
    const response = await fetch(`http://127.0.0.1:8080/log/list?page=${page}`);
    const result: LogResponse = await response.json();

    if (result.content && result.content.length > 0) {
      data.value = [...data.value, ...result.content]; // Add new logs
      hasMoreLogs.value = currentPage.value < result.totalPages - 1; // Check if there are more pages
    } else {
      hasMoreLogs.value = false; // Hide "Load More" button if no more logs
    }
  } catch (error) {
    console.error('Error fetching logs:', error);
  } finally {
    status.value = 'done';
  }
};

// Initialize log loading on the first page
loadLogs(currentPage.value);

// Load more logs when button is clicked
const loadMore = () => {
  if (hasMoreLogs.value) {
    currentPage.value++;
    loadLogs(currentPage.value);
  }
};

// Handle scroll event to show/hide the scroll indicators
const handleScroll = (event: Event) => {
  const scrollContainer = event.target as HTMLElement;
  const scrollPosition = scrollContainer.scrollTop;
  const scrollHeight = scrollContainer.scrollHeight;
  const clientHeight = scrollContainer.clientHeight;

  // Show scroll indicators based on scroll position
  showScrollTop.value = scrollPosition > 100; // Show top scroll indicator if scrolled down more than 100px
  showScrollBottom.value = scrollPosition + clientHeight < scrollHeight - 100; // Show bottom scroll indicator if not at the bottom
};
</script>

<style lang="postcss">
body {
  font-family: Geist, serif;
  color: white;
}

::-webkit-scrollbar {
  height: 2px;
  width: 5px;
  border-radius: 10px;
}

::-webkit-scrollbar-track {
  background-color: #222;
  border-radius: 10px;
}

::-webkit-scrollbar-track:active,::-webkit-scrollbar-track:hover {
  background-color: #222;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb,::-webkit-scrollbar-thumb:hover {
  background-color: #ffffff;
  border: 0 solid #fff;
  -webkit-transition: all .5s;
  transition: all .5s;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:active {
  background-color: #ffffff;
  border: 0 solid #fff;
  border-radius: 0;
  -webkit-transition: all .5s;
  transition: all .5s;
}
</style>