# Implementation Plan - Fix NullPointerException in MovieAdapter

The application crashes with a `NullPointerException` in `MovieAdapter.onBindViewHolder` when trying to call `getContext()` on `holder.itemView`.

## Analysis
The `MovieHolder` class in `MovieAdapter.java` has a redundant `itemView` field that shadows the `itemView` field from the parent `RecyclerView.ViewHolder` class. In the `MovieHolder` constructor, this shadow field is never properly initialized because the assignment `itemView = itemView;` refers to the constructor parameter rather than the class field. Consequently, `holder.itemView` is `null` when accessed in `onBindViewHolder`.

## Proposed Changes

### [Component] Adapters

#### [MODIFY] [MovieAdapter.java](file:///Users/mackendy.belizaire/StudioProjects/Flixster/app/src/main/java/com/my_app/flixster/adapters/MovieAdapter.java)
- Remove the redundant `public View itemView;` field declaration in the `MovieHolder` class.
- Remove the `itemView = itemView;` assignment in the `MovieHolder` constructor.
- The parent `RecyclerView.ViewHolder` already provides and initializes a public `itemView` field via `super(itemView)`.

## Verification Plan

### Manual Verification
- Deploy the app to a device or emulator.
- Verify that the list of movies loads without crashing.
- Verify that images are correctly loaded using Glide.